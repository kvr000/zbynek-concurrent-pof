package cz.znj.kvr.sw.pof.concurrent.lwfuture.concurrent.test;

import com.google.common.util.concurrent.MoreExecutors;
import cz.znj.kvr.sw.pof.concurrent.lwfuture.concurrent.DistinguishFutureListener;
import cz.znj.kvr.sw.pof.concurrent.lwfuture.concurrent.benchmark.BenchmarkSupport;
import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Warmup;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class SinglePreAsyncListenerTest
{
	public static final int         WARMUP_ITERATIONS = BenchmarkSupport.WARMUP_ITERATIONS;
	public static final int         COUNT = BenchmarkSupport.COUNT;

	@Test
	public void                     disassembleLwfuture() throws ExecutionException, InterruptedException
	{
		cz.znj.kvr.sw.pof.concurrent.lwfuture.concurrent.ListenableFutureTask[] array = BenchmarkSupport.populateLwFutureArray(COUNT);
		for (cz.znj.kvr.sw.pof.concurrent.lwfuture.concurrent.ListenableFutureTask f: array) {
			f.addListener(new DistinguishFutureListener());
		}
		BenchmarkSupport.threadedRunFutures(array);
		for (cz.znj.kvr.sw.pof.concurrent.lwfuture.concurrent.ListenableFutureTask f: array) {
			f.get();
		}
	}
}
