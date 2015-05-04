int x;

int foo(int a)
{
  int b;
  x = x + a;
  for (b = 0; b < 5; b = b + 1)
  {
    int a;
    a = b;
    x = x + a;
  }
  return a;
}

int main()
{
  x = 1;
  return foo (3);
}

