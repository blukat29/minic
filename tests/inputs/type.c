int main()
{
  int a[4];
  float b[3];
  a[1.4] = 1;
  b[0] = 2;
  a[1] = 1 + 2.7;
  a[2] = a;
  a[0] = a[1] + a[2];
  a[0] = a + a[2];
  return 1;
}
