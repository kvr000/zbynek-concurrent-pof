# zbynek-concurrent-pof

Zbynek's concurrent experiments and proofs of concepts

Currently there is only single project:

## [Lock free ListenableFuture / Future implementation](zbynek-lwfuture-pof/)

The project implements java concurrent (Listenable) Future in very cheap and flexible way.

The performance is for obvious reasons (additional support of listeners) slightly lower than original JDK Future but significantly higher than similar implementations in Guava and Spring.

### Flexibility

Additionally it solves several design issues from which suffer Guava and Spring implementations:
- allows several types of listeners, which can just implement Runnable or receive the original Future or receive directly the result to appropriate method
- distinguishes failure and cancellation when invoking listener
- distinguishes not started and running state
- allows delayed cancel notifications, i.e. the notification about cancel can be postponed until the task really exits, this is useful when task occupies some shared resource like network port

### Performance

The performance comparison looks like (measured on my low voltage i7 x86_64):
```
Benchmark                                           Mode  Cnt    Score   Error  Units
SinglePreListenerAsyncBenchmark.benchmarkGuava     thrpt    2   99.828          ops/s
SinglePreListenerAsyncBenchmark.benchmarkJdk       thrpt    2  228.109          ops/s
SinglePreListenerAsyncBenchmark.benchmarkLwFuture  thrpt    2  165.285          ops/s
SinglePreListenerAsyncBenchmark.benchmarkSpring    thrpt    2   82.545          ops/s
```

It's 65% faster than Guava, 100% faster than Spring and 38% slower than JDK (but JDK test runs without  listener).

## License

The code is released under version 2.0 of the [Apache License][].

## Stay in Touch

Feel free to contact me at kvr@centrum.cz or http://kvr.znj.cz/about/ and http://github.com/kvr000

[Apache License]: http://www.apache.org/licenses/LICENSE-2.0
