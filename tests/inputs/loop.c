int main()
{
  int i;
  int s;
  s = 0;
  for (i=0; i < 10; i= i + 1)
  {
    s = s + i;
  }
  while (i < 100)
  {
    i = i + 20;
    printf(i);
  }
  do {
    s = s - 7;
    printf(s);
  } while (s > 7);
  return s + i;
}
