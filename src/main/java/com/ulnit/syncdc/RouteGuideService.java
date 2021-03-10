package com.ulnit.syncdc;

import javax.inject.Singleton;

import com.ulnit.syncdc.proto.Feature;
import com.ulnit.syncdc.proto.RouteGuideGrpc.RouteGuideImplBase;



// @Singleton
// public class RouteGuideService extends  RouteGuideImplBase {

//     @Override
//     public void getFeature(com.ulnit.syncdc.proto.Point request, io.grpc.stub.StreamObserver<com.ulnit.syncdc.proto.Feature> responseObserver) {
//         System.out.println("Service getFeature");
//         responseObserver.onNext(Feature.newBuilder().setLocation(request).build());         
//         responseObserver.onCompleted();  
//     }

//     @Override
//     public void listFeatures(com.ulnit.syncdc.proto.Rectangle request,
//     io.grpc.stub.StreamObserver<com.ulnit.syncdc.proto.Feature> responseObserver) {
//         System.out.println("Service listFeatures");
//         throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
//     }
// }
