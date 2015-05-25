float normsq(float vec[2]) {
  float sum;
  sum = vec[0]*vec[0] + vec[1]*vec[1];
  return sum;
}

int max(int a, int b) {
  if (a > b)
    return a;
  else
    return b;
}

int main() {
  float v[2];
  float n;
  int m;
  v[0] = 1.0;
  v[1] = 2.0;
  n = normsq(v);
  m = max(1.2, 3.5);
  m = max(1,2,3,4,5);
  return 0;
}
