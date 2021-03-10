package com.ulnit.syncdc;

import javax.inject.Singleton;

import com.ulnit.syncdc.proto.MutinyRouteGuideGrpc;


// @Singleton
// public class ReactiveRouteGuide extends  MutinyRouteGuideGrpc.RouteGuideImplBase {

//     @Override
//     public io.smallrye.mutiny.Uni<com.ulnit.syncdc.proto.RouteSummary> recordRoute(io.smallrye.mutiny.Multi<com.ulnit.syncdc.proto.Point> request) {
//         System.out.println("Service recordRoute");
//         throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
//     }

//     @Override
//     public io.smallrye.mutiny.Multi<com.ulnit.syncdc.proto.RouteNote> routeChat(io.smallrye.mutiny.Multi<com.ulnit.syncdc.proto.RouteNote> request) {
//         System.out.println("Service routeChat");
//         throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
//     }
// }
