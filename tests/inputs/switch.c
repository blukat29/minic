int main()
{
  switch (c)
  {
    case 100:
      a = 1;
      b = 2;
      break;
    case 200:
      a = 3;
    case 300:
    case 400:
      b = 4;
      break;
    default:
      a = 8;
  }
}
