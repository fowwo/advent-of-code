// December 4th, 2022
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int cmp(const void* a, const void* b) {
	int x = *(int *) a;
	int y = *(int *) b;
	if (x < y) return -1;
	if (x > y) return 1;
	return 0;
}

int main(){
	FILE *f = fopen("input.txt", "r");
	char line[10000];
	char copy[10000];
	fgets(line, 10000, f);
	strcpy(copy, line);
	fclose(f);

	int n = 0;
	char* token;
	token = strtok(copy, ",");
	while (token != NULL) {
		n++;
		token = strtok(NULL, ",");
	}

	int list[n];
	int i = 0;
	token = strtok(line, ",");
	while (token != NULL) {
		list[i] = atoi(token);
		i++;
		token = strtok(NULL, ",");
	}

	qsort(list, n, sizeof(int), cmp);
	int center = list[n / 2];
	int fuel = 0;
	for (i = 0; i < n; i++) {
		fuel += abs(list[i] - center);
	}
	printf("%d\n", fuel);
	return 0;
}
