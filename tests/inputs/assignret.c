int main()
{
  int x;
  int a[10];
  x = 3;
  a[2] = 5;
  a[x] = 8;
  return a[x-1];
}
