# Spring Exposed Sample

The main goal of this sample is to show a HandlerInterceptor which works similarly to Hibernate's `OpenSessionInViewInterceptor`.

`ExposedTransactionPerRequestInterceptor` creates a transaction per request and rollbacks it if an exception occurred.

This is useful only if you're exposing your DAO-entities out of the service layer, since Exposed requires a transaction in the context when accessing any entity's fields. In the other cases `@Transactional` service methods are more suitable.

