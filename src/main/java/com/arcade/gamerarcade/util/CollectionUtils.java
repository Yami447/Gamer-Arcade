package com.arcade.gamerarcade.util;

import java.util.*;

public class CollectionUtils {
    private static final float DEFAULT_LOAD_FACTOR = 0.75F;

    public CollectionUtils() {}

    public static final <T> boolean isEmpty(Collection<T> c) {
        return !isNotEmpty(c);
    }

    public static <T> boolean isNotEmpty(Collection<T> c) {
        return c != null && c.size() > 0;
    }

    public static <T> boolean checkIfPresent(Collection<T> c, T d) {
        return (isNotEmpty(c) && c.contains(d));
    }

    public static <K, V> boolean isEmpty(Map<K, V> m) {
        return !isNotEmpty(m);
    }

    public static <K, V> boolean isNotEmpty(Map<K, V> m) {
        return m != null && m.size() > 0;
    }

    public static <K, V> Map<K, V> nullSafeMap(Map<K, V> m) {
        return (Map<K, V>)(m != null ? m : new HashMap());
    }

    public static <T> List<T> nullSafeList(List<T> c) {
        return (List<T>)(c != null ? c : new ArrayList());
    }

    public static <T> Set<T> nullSafeSet(Set<T> s) {
        return (Set<T>)(s != null ? s : new HashSet());
    }

    public static <T> List<T> nullSafeEmptyList(List<T> list) {
        return list == null ? Collections.emptyList() : list;
    }

    public static <T> Set<T> nullSafeEmptySet(Set<T> set) {
        return set == null ? Collections.emptySet() : set;
    }

    public static <T> List<T> nullSafeCollection(Collection<T> c) {
        return c != null ? new ArrayList(c) : new ArrayList();
    }

    public static <T> Collection<T> nullSafeEmptyCollection(Collection<T> c) {
        return (Collection<T>)(c == null ? Collections.emptyList() : c);
    }

    public static <K, V> Map<K, V> transformToLinkedHashMap(Collection<V> c, Transformer<V, K> t) {
        HashMap<K, V> toRet = new LinkedHashMap(getInitialCapacityOfMap(c));
        if (isEmpty(c)) {
            return toRet;
        } else {
            for(V v : c) {
                K key = t.transform(v);
                toRet.put(key, v);
            }

            return toRet;
        }
    }

    public static <K, V> Map<K, V> transformToMap(Collection<V> c, Transformer<V, K> t) {
        HashMap<K, V> toRet = new HashMap(getInitialCapacityOfMap(c));
        if (isEmpty(c)) {
            return toRet;
        } else {
            for(V v : c) {
                K key = t.transform(v);
                toRet.put(key, v);
            }

            return toRet;
        }
    }

    public static <T, U> List<U> transformToList(Collection<T> c, Transformer<T, U> t) {
        List<U> list = new ArrayList(getInitialCapacityOfList(c));
        if (isEmpty(c)) {
            return list;
        } else {
            for(T object : c) {
                list.add(t.transform(object));
            }

            return list;
        }
    }

    public static <T> List<T> transformToList(Collection<T> c) {
        List<T> list = new ArrayList(getInitialCapacityOfList(c));
        if (isNotEmpty(c)) {
            list.addAll(c);
        }

        return list;
    }

    public static <T> Set<T> transformToSet(Collection<T> c) {
        Set<T> set = new HashSet(getInitialCapacityOfSet(c));
        if (isNotEmpty(c)) {
            set.addAll(c);
        }

        return set;
    }

    public static <T, U> Set<U> transformToSet(Collection<T> c, Transformer<T, U> t) {
        Set<U> set = new HashSet(getInitialCapacityOfList(c));
        if (c.isEmpty()) {
            return set;
        } else {
            for(T object : c) {
                set.add(t.transform(object));
            }

            return set;
        }
    }

    public static <T> Set<T> transformToLinkedHashSet(Collection<T> c) {
        LinkedHashSet<T> set = new LinkedHashSet(getInitialCapacityOfSet(c));
        if (isNotEmpty(c)) {
            set.addAll(c);
        }

        return set;
    }

    public static <K, V> Map<K, List<V>> groupBy(Map<K, V> map, Transformer<K, V> transformer) {
        Map<K, List<V>> response = new HashMap(getInitialCapacityOfMap(map.keySet()));

        for(Map.Entry<K, V> entry : map.entrySet()) {
            List<V> values = (List)response.computeIfAbsent(entry.getKey(), (k) -> new ArrayList());
            values.add(entry.getValue());
        }

        return response;
    }

    public static <K, T> Map<K, List<T>> groupBy(Collection<T> c, Transformer<T, K> transformer) {
        Map<K, List<T>> map = new HashMap(getInitialCapacityOfMap(c));

        for(T t : nullSafeCollection(c)) {
            K key = transformer.transform(t);
            List<T> list = (List)map.computeIfAbsent(key, (k) -> new ArrayList());
            list.add(t);
        }

        return map;
    }

//    public static <T> List<T> filteredList(Collection<T> c, Transformer<T, Boolean> filterTransformer) {
//        List<T> list = Lists.newArrayList();
//
//        for(T t : nullSafeCollection(c)) {
//            Boolean allow = filterTransformer.transform(t);
//            if (TBooleanUtils.isTrue(allow)) {
//                list.add(t);
//            }
//        }
//
//        return list;
//    }

//    public static <T, M> List<M> filterAndTransformToList(Collection<T> collection, Transformer<T, Boolean> filterTransformer, Transformer<T, M> transformer) {
//        List<M> list = Lists.newArrayList();
//
//        for(T t : nullSafeCollection(collection)) {
//            Boolean allow = filterTransformer.transform(t);
//            if (TBooleanUtils.isTrue(allow)) {
//                list.add(transformer.transform(t));
//            }
//        }
//
//        return list;
//    }

//    public static <T, M> Map<M, T> filterAndTransformToMap(Collection<T> collection, Transformer<T, Boolean> filterTransformer, Transformer<T, M> transformer) {
//        Map<M, T> map = Maps.newHashMap();
//
//        for(T t : nullSafeCollection(collection)) {
//            Boolean allow = filterTransformer.transform(t);
//            if (TBooleanUtils.isTrue(allow)) {
//                map.put(transformer.transform(t), t);
//            }
//        }
//
//        return map;
//    }

    public static <K, V, M> Map<K, M> transformToKeyAndMap(Collection<V> c, Transformer<V, K> keyTransformer, Transformer<V, M> valueTransformer) {
        Map<K, M> map = new HashMap(getInitialCapacityOfMap(c));
        if (isEmpty(c)) {
            return map;
        } else {
            for(V v : c) {
                map.put(keyTransformer.transform(v), valueTransformer.transform(v));
            }

            return map;
        }
    }

    public static <K, V> V safeGet(Map<K, V> m, K key) {
        return (V)(isNotEmpty(m) ? m.get(key) : null);
    }

    public static <K, V> String safeGetAsString(Map<K, V> m, K key) {
        V v = (V)safeGet(m, key);
        return v != null ? String.valueOf(v) : null;
    }

    public static <K, V> Integer safeGetInt(Map<K, V> m, K key) {
        V v = (V)safeGet(m, key);
        return v != null ? Integer.valueOf(String.valueOf(v)) : null;
    }

    public static <T> T safeGetAtIndex(List<T> list, int index) {
        if (list == null) {
            return null;
        } else {
            return (T)(list.size() <= index ? null : list.get(index));
        }
    }

    public static <K, V> V safeGetFirst(Map<K, List<V>> m, K key) {
        return (V)safeGetAtIndex((List)safeGet(m, key), 0);
    }

    private static int getInitialCapacityOfSet(Collection c) {
        return getInitialCapacityOfMap(c);
    }

    private static int getInitialCapacityOfMap(Collection c) {
        int size = 1;
        if (isNotEmpty(c)) {
            size = (int)Math.ceil((double)((float)c.size() / 0.75F));
        }

        return size;
    }

    private static int getInitialCapacityOfList(Collection c) {
        int size = 0;
        if (isNotEmpty(c)) {
            size = c.size();
        }

        return size;
    }

    private static class EM {
        private String s1;
        private String s2;

        private EM() {
        }

        public String getS1() {
            return this.s1;
        }

        public String getS2() {
            return this.s2;
        }
    }

    public interface Transformer<K, V> {
        V transform(K var1);
    }
}
