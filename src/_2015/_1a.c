// December 10th, 2019
#include <stdlib.h>
#include <stdio.h>

int main(){
	FILE *f = fopen("input/2015/1.txt", "r");
	char c = fgetc(f);
	int total = 0;
	while (c != EOF){
		if (c == '('){
			total++;
		} else {
			total--;
		}
		c = fgetc(f);
	}
	fclose(f);

	printf("Total: %d\n", total);
	return 0;
}
