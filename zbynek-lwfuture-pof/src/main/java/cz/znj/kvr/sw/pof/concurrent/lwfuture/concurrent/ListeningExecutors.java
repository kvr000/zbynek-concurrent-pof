/*
 * Copyright 2015 Zbynek Vyskovsky mailto:kvr@centrum.cz http://kvr.znj.cz/ http://github.com/kvr000/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cz.znj.kvr.sw.pof.concurrent.lwfuture.concurrent;


import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * Utility class to wrap executors to listenable versions.
 *
 * @author
 * 	Zbynek Vyskovsky, mailto:kvr@centrum.cz http://kvr.znj.cz/software/java/ListenableFuture/ http://github.com/kvr000
 */
public final class ListeningExecutors
{
	private                         ListeningExecutors()
	{
	}

	/**
	 * Decorates passed {@code executorService} by providing listening functionality.
	 *
	 * @param executorService
	 *      original executor service
	 *
	 * @return
	 *      listenable version of execution service
	 */
	public static ListeningExecutorService listeningDecorator(ExecutorService executorService)
	{
		return executorService instanceof ListeningExecutorService ?
			(ListeningExecutorService)executorService :
				executorService instanceof ScheduledExecutorService ?
					listeningDecorator((ScheduledExecutorService)executorService) :
					new ForwardingListeningExecutorService(executorService);
	}

	/**
	 * Decorates passed {@code executorService} by providing listening functionality.
	 *
	 * @param executorService
	 *      original executor service
	 *
	 * @return
	 *      listenable version of execution service
	 */
	public static ListeningScheduledExecutorService listeningDecorator(ScheduledExecutorService executorService)
	{
		return executorService instanceof ListeningScheduledExecutorService ?
				(ListeningScheduledExecutorService)executorService :
				new ForwardingListeningScheduledExecutorService(executorService);
	}

	/**
	 * Gets executor executing the runnables in current thread.
	 *
	 * @return
	 *      executor executing the runnables in current thread
	 */
	public static Executor          directExecutor()
	{
		return DirectExecutor.getInstance();
	}

	/**
	 * Gets executor executing the tasks in current thread.
	 *
	 * @return
	 *      executor executing the tasks in current thread
	 */
	public static ListeningExecutorService          directExecutorService()
	{
		return DirectExecutorService.getInstance();
	}

	/**
	 * Gets executor rejecting all tasks.
	 *
	 * @return
	 *      executor rejecting all requests
	 */
	public static Executor          rejectingExecutor()
	{
		return RejectingExecutor.getInstance();
	}

	/**
	 * Gets executor service rejecting all tasks.
	 *
	 * @return
	 *      executor service rejecting all requests
	 */
	public static ListeningExecutorService rejectingExecutorService()
	{
		return RejectingExecutorService.getInstance();
	}
}
