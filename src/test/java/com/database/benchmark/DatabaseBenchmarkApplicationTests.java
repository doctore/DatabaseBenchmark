package com.database.benchmark;

import com.database.benchmark.jooq.dto.OrderLineDto;
import com.database.benchmark.jpa.model.OrdersLine;
import com.database.benchmark.jpa.repository.OrdersLineRepository;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@State(Scope.Benchmark)
@BenchmarkMode(Mode.All)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class DatabaseBenchmarkApplicationTests {

    private static OrdersLineRepository repositoryJPA;
    private static com.database.benchmark.jdbc.repository.OrdersLineRepository repositoryJDBC;
    private static com.database.benchmark.r2dbc.repository.OrdersLineRepository repositoryR2DBC;
    private static com.database.benchmark.jooq.dao.OrderLineDao repositoryJOOQ;

    @Autowired
    public void setRepositoryJPA(OrdersLineRepository repository) {
        DatabaseBenchmarkApplicationTests.repositoryJPA = repository;
    }

    @Autowired
    public void setRepositoryJDBC(com.database.benchmark.jdbc.repository.OrdersLineRepository repository) {
        DatabaseBenchmarkApplicationTests.repositoryJDBC = repository;
    }

    @Autowired
    public void setRepositoryR2DBC(com.database.benchmark.r2dbc.repository.OrdersLineRepository repository) {
        DatabaseBenchmarkApplicationTests.repositoryR2DBC = repository;
    }

    @Autowired
    public void setRepositoryJOOQ(com.database.benchmark.jooq.dao.OrderLineDao repository) {
        DatabaseBenchmarkApplicationTests.repositoryJOOQ = repository;
    }


    @Test
    public void runBenchmarks() throws Exception {
        Options opts = new OptionsBuilder()
                // Set the class name regex for benchmarks to search for to the current class
                .include("\\." + this.getClass().getSimpleName() + "\\.")
                .warmupIterations(1)
                .measurementIterations(2)
                // Do not use forking or the benchmark methods will not see references stored within its class
                .forks(0)
                // Do not use multiple threads
                .threads(1)
                .shouldDoGC(true)
                .shouldFailOnError(true)
                .jvmArgs("-server")
                // Add result format
                .resultFormat(ResultFormatType.CSV)
                .result("benchmark-result/" + System.currentTimeMillis() + ".csv")
                .build();
        new Runner(opts).run();
    }


    @Benchmark
    public void findOrdersLineBetweenIdsWithOrdersAndPizzaJPA(LowerUpperIds parameters) {
        String[] lowerAndUpperId = parameters.splitLowerUpperId();
        int lowerId = Integer.parseInt(lowerAndUpperId[0]);
        int upperId = Integer.parseInt(lowerAndUpperId[1]);

        for (int i = 0; i < parameters.iterations; i++) {
            List<OrdersLine> result = repositoryJPA.findByIdBetween(lowerId, upperId);
        }
    }

    @Benchmark
    public void findOrdersLineBetweenIdsWithOrdersAndPizzaJDBC(LowerUpperIds parameters) {
        String[] lowerAndUpperId = parameters.splitLowerUpperId();
        int lowerId = Integer.parseInt(lowerAndUpperId[0]);
        int upperId = Integer.parseInt(lowerAndUpperId[1]);

        for (int i = 0; i < parameters.iterations; i++) {
            List<com.database.benchmark.jdbc.model.OrdersLine> result = repositoryJDBC.findBetweenIdsWithOrdersAndPizza(lowerId, upperId);
        }
    }

    @Benchmark
    public void findOrdersLineBetweenIdsWithOrdersAndPizzaR2DBC(LowerUpperIds parameters) {
        String[] lowerAndUpperId = parameters.splitLowerUpperId();
        int lowerId = Integer.parseInt(lowerAndUpperId[0]);
        int upperId = Integer.parseInt(lowerAndUpperId[1]);

        for (int i = 0; i < parameters.iterations; i++) {
            List<com.database.benchmark.r2dbc.model.OrdersLine> result = repositoryR2DBC.findBetweenIdsWithOrdersAndPizza(lowerId, upperId)
                    .collectList()
                    .block();
        }
    }

    @Benchmark
    public void findOrdersLineBetweenIdsWithOrdersAndPizzaJOOQ(LowerUpperIds parameters) {
        String[] lowerAndUpperId = parameters.splitLowerUpperId();
        int lowerId = Integer.parseInt(lowerAndUpperId[0]);
        int upperId = Integer.parseInt(lowerAndUpperId[1]);

        for (int i = 0; i < parameters.iterations; i++) {
            List<OrderLineDto> result = repositoryJOOQ.fetchToOrderLineDtoByOrderIdWithOrderAndPizzaDto(lowerId, upperId);
        }
    }


    @State(value = Scope.Benchmark)
    public static class LowerUpperIds {

        @Param({ "2", "3" })
        int iterations;

        @Param({ "25-75", "100-250", "1000-1500", "10000-11000", "100000-102000" })
        String lowerUpperId;

        String[] splitLowerUpperId() {
            return lowerUpperId.split("-");
        }
    }

}
