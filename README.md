# RxLifecycle
Easy handling lifecycle for Android application. Supports only RxJava2.

## Gradle
```gradle
    implementation 'im.wangchao:rxlifecycle:0.1.0'
    implementation 'io.reactivex.rxjava2:rxjava:2.1.2'
```

## How to use
Observe the lifecycle events.
```java
RxLifecycle.with(this)
        .toObservable()
        .subscribe(integer -> Log.e("wcwcwc", "lifecycle: " + integer));
```
Compose `transform` to your original observable, then automatically complete the observable until lifecycle `onDestroy`.
```java
// transform
RxLifecycle.with(this).bindUntilDestroy();
...

// auto complete until destroy
Observable.interval(1, TimeUnit.SECONDS)
        .compose(RxLifecycle.with(this).bindUntilDestroy())
        .subscribe(i -> Log.e("wcwcwc", "interval: " + i));
```
You can also customize the event.
```
Observable.interval(1, TimeUnit.SECONDS)
        .compose(RxLifecycle.with(this).bindUntilEvent(RxLifecycle.EVENT_ON_STOP))
        .subscribe(i -> Log.e("wcwcwc", "interval: " + i));
```
