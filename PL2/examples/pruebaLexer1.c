/* Comentario de 
varias lineas */

// Esto es un comentario de un a linea

int x;
int y=5;
char c=5, x;
char d[2];
char m[2][2];
float f;

void mult(){
  int z;
  x = 2;
  y = 3;
  z = x * paramsInv(4);
  
  printf("El resultado es %d", z);
}

int rest(){
  int z;
  int pq;
  x = 2;
  y = 3;
  z *= x - y;
  y = z;
  
  printf("El resultado es %d", z);
  
  return z;
}

void paramsInv(int p){
  int z;
  x = 2;
  y = 3;
  z = x - rest();
  ;
  
  printf("El resultado es %d", z);
  
  return z;
}

void multParams(int p, int h, char c[5]){}
