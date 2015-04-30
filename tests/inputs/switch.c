int main()
{
  int a, c;
  a = 1;
  c = 100;
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
  return a;
}
