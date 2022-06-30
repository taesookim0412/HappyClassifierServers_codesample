//package com.happyclassifier.happyclassiferstore.interceptors;
//
//import org.springframework.stereotype.Repository;
//import reactor.core.publisher.Flux;
//
//import java.util.List;
//
// Avoid this at all costs!
//public interface FluxInterceptor<T> {
//    public default List<T> toList(Flux<T> items){
//        return items.collectList().block();
//    }
//}
