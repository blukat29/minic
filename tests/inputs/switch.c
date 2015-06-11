int test(int c)
{
  int a;
  switch (c)
  {
    case 100:
      a = 1;
      break;
    case 200:
      a = 2;
    case 300:
    case 400:
      a = 4;
      break;
    default:
      a = 8;
  }
  printf(a);
  return a;
}

int main()
{
  test(0);
  test(100);
  test(123);
  test(200);
  test(300);
  test(400);
  test(500);
  return 0;
}
