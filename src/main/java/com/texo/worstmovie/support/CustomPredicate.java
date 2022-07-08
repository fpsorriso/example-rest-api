package com.texo.worstmovie.support;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public interface CustomPredicate<T> extends Predicate<T> {
    static <T> Predicate<T> distinctBy(Function<? super T, Object> function) {
        final var map = new ConcurrentHashMap<>();
        return t -> Objects.isNull(map.putIfAbsent(function.apply(t), Boolean.TRUE));
    }
}
