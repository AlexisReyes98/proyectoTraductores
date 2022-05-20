n = 9;

while n > 0 {

  # las expresiones tienen parentesis
  if (n % 2 == 0) {
    log n + " -> par";
  }
  else {
    log n + " -> impar";
  }

  n = n - 1;
}