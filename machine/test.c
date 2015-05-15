#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define N 5

int square[N][N];

void show()
{
  int i, j;
  for (i=0; i<N; i++)
  {
    for (j=0; j<N; j++)
      printf("%2d ", square[i][j]);
    printf("\n");
  }
  printf("\n");
}

// http://en.wikipedia.org/wiki/Siamese_method
void build()
{
  int i = 1;
  int x = N / 2;
  int y = 0;
  int nx, ny;
  while (i <= (N * N))
  {
    square[y][x] = i;
    ny = (y == 0)? N - 1 : y - 1;
    nx = (x == N - 1)? 0 : x + 1;
    if (square[ny][nx] != 0)
      y = (y == N - 1)? 0 : y + 1;
    else {
      x = nx;
      y = ny;
    }
    i += 1;
  }
}

int main()
{
  build();
  show();
  return 0;
}
