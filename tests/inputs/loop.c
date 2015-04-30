int main()
{
  int i;
  int s;
  s = 0;
  for (i=0; i < 10; i= i + 1)
  {
    s = s + i;
  }
  while (i < 20)
  {
    i = i + 2;
  }
  do {
    s = s - 10;
  } while (s > 0);
  return s;
}
