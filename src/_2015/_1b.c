// December 10th, 2019
#include <stdlib.h>
#include <stdio.h>

int main(){
	FILE *f = fopen("input/2015/1.txt", "r");
	char c = fgetc(f);
	int index = 1;
	int total = 0;
	while (c != EOF){
		if (c == '('){
			total++;
		} else {
			total--;
		}
		if (total < 0){
			printf("In the basement at index %d\n", index);
			return 0;
		}
		index++;
		c = fgetc(f);
	}
	fclose(f);

	printf("Total: %d\n", total);
	return 0;
}
