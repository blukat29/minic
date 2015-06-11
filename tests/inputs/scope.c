int x;
float y[5];

int foo(int a)
{
  int b;
  x = x + a;
  for (b = 0; b < 5; b = b + 1)
  {
    int a;
    a = b;
    x = x + a;
    y[b] = b / 10.0;
    printf(a);
  }
  printf(a);
  return a;
}

float main()
{
  x = 800;
  foo (30);
  printf(x);
  return x + y[2];
}

