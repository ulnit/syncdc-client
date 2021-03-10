package com.ulnit.syncdc;

import java.util.Random;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ulnit.syncdc.proto.MutinyRouteGuideGrpc;
import com.ulnit.syncdc.proto.Point;
import com.ulnit.syncdc.proto.Rectangle;
import com.ulnit.syncdc.proto.RouteGuideGrpc;
import com.ulnit.syncdc.proto.RouteNote;
import com.ulnit.syncdc.proto.RouteSummary;

import io.grpc.Channel;
import io.quarkus.grpc.runtime.annotations.GrpcService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

@Path("/routeguide")
public class GreetingResource {

    @Inject
    @GrpcService("rg")                     
    MutinyRouteGuideGrpc.MutinyRouteGuideStub mutinyClient;

    @Inject
    @GrpcService("rg")                     
    RouteGuideGrpc.RouteGuideBlockingStub blockingClient; 

    @Inject
    @GrpcService("rg")
    Channel channel;

    @GET
    @Path("feature")
    public void printFeature() {
        // Retrieve a stream
        Point p = Point.newBuilder().setLatitude(409146138).setLongitude(-746188906).build();
        blockingClient.getFeature(p);
        //mutinyClient.getFeature(p).onItem().invoke(t -> System.out.println(t.getLocation().getLatitude()));
    }

    @GET
    @Path("features")
    public void printFeatures() {
        // Retrieve a stream
        Rectangle ra  = Rectangle.newBuilder().setLo(Point.newBuilder().setLatitude(400000000).setLongitude(-750000000).build())
            .setHi(Point.newBuilder().setLatitude(420000000).setLongitude(-730000000).build()).build();
        mutinyClient.listFeatures(ra).onItem().invoke(t -> System.out.println(t.getAllFields()));
    }

    @GET
    @Path("record")
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<RouteSummary> recordRoute() {
        // Send a stream and wait for completion
        Multi<Point> inputs = Multi.createFrom().range(0, 12)
                .map(i -> Point.newBuilder().setLatitude(i).setLongitude(i*33+100).build());
        return mutinyClient.recordRoute(inputs);
    }

    @GET
    @Path("chat")
    @Produces(MediaType.TEXT_PLAIN)
    public Multi<String> routeGuide() {
        RouteNote rn1 = RouteNote.newBuilder().setMessage("1 message").setLocation(Point.newBuilder().setLatitude(0).setLongitude(1).build()).build();
        RouteNote rn2 = RouteNote.newBuilder().setMessage("2 message").setLocation(Point.newBuilder().setLatitude(0).setLongitude(2).build()).build();
        RouteNote rn3 = RouteNote.newBuilder().setMessage("3 message").setLocation(Point.newBuilder().setLatitude(0).setLongitude(3).build()).build();
        return mutinyClient.routeChat(Multi.createFrom().items(Stream.of(rn1, rn2, rn3)).onItem().transform(l -> RouteNote.newBuilder().setMessage("req:"+l).setLocation(Point.newBuilder().setLatitude(8).setLongitude((new Random()).nextInt(10)).build()).build())).onItem().transform(RouteNote::getMessage);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }
}