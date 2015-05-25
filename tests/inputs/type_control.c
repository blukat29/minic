float foo() {
  int a[4];
  float b[2];
  int x;

  if (a)
    return a;
  else
    x = 1;

  for (x = 0; a; x = x + 1) {
  }

  switch (a) {
    case 1: x = 1; break;
  }

  switch (b[1]) {
    case 1: x = 1; break;
  }

  while (a) {
    x = x + 1;
  }
  return 100;
}
