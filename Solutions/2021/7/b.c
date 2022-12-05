// December 4th, 2022
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

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

	int m = 2147483647;
	int c = 2147483647;
	for (i = 0; c <= m; i++) {
		m = c;
		c = 0;
		for (int j = 0; j < n; j++) {
			int x = abs(list[j] - i);
			c += x * (x + 1) / 2;
		}
	}
	printf("%d\n", m);
	return 0;
}
