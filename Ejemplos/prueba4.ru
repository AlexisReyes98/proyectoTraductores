log "secuencia Fibonacci";
a = 0;
b = 1;
while b <= 6765 {
    log b;
    b = a + b;
    a = b - a;
}