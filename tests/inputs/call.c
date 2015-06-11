int one() {
  return 1;
}

int add(int x, int y) {
  int s;
  s = x + y;
  return s;
}

float var(float a[3]) {
  float s;
  int i;
  s = 0.0;
  for (i=0; i<3; i=i+1) {
    s = s + a[i]*a[i];
  }
  return s;
}

float main()
{
  int s, x;
  float a[3];
  float v;
  s = add (1, 2);
  x = one();
  a[0] = 1.0; a[1] = 2.0; a[2] = 3.0;
  v = var(a);
  return s * 10 + x + v / 100.0;
}
