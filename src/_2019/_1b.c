// December 10th, 2019
#include <stdlib.h>
#include <stdio.h>

int main(){
	FILE *f = fopen("input/2019/1.txt","r");
	char line[100];
	char *s = fgets(line, 100, f);

	int total = 0;
	while(s != NULL) {
		int n = atoi(line);
		n = n / 3 - 2;
		while (n > 0){
			total += n;
			n = n / 3 - 2;
		}
		s = fgets(line, 100, f);
	}
	printf("total: %d\n", total);

	free(s);
	fclose(f);
	return 0;
}
