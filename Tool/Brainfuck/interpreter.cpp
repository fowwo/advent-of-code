// December 2nd, 2024

#include <fstream>
#include <iostream>
#include <stack>
#include <string>
#include <vector>

int main(int argc, char *argv[]) {
	if (argc == 1) {
		std::cerr << std::endl << "Missing file path." << std::endl;
		return 1;
	}

	// Open the brainfuck file.
	std::ifstream file(argv[1]);
	if (!file.is_open()) {
		std::cerr << std::endl << "Unable to open file." << std::endl;
		return 1;
	}

	// Parse the code, removing comments.
	std::vector<char> program;
	char c;
	while (file.get(c)) {
		switch (c) {
			case '+':
			case '-':
			case '<':
			case '>':
			case '[':
			case ']':
			case '.':
			case ',':
				program.push_back(c);
		}
	}
	file.close();

	// Find bracket pairs for loop execution.
	std::vector<int> loop(program.size(), -1);
	std::stack<int> s;
	for (int i = 0; i < program.size(); i++) {
		if (program[i] == '[') {
			s.push(i);
		} else if (program[i] == ']') {
			if (s.empty()) {
				std::cerr << std::endl << "Error: Unmatched closing bracket. (index " << i << ")" << std::endl;
				return -1;
			}
			int x = s.top();
			loop[x] = i + 1;
			loop[i] = x + 1;
			s.pop();
		}
	}
	if (!s.empty()) {
		std::cerr << std::endl << "Error: Unmatched opening bracket. (index " << s.top() << ")" << std::endl;
		return -1;
	}

	// Run the code.
	const int ARRAY_SIZE = 30000;
	unsigned char array[ARRAY_SIZE] = {};
	int i = 0, p = 0;
	while (i < program.size()) {
		switch (program[i]) {
			case '+':
				array[p]++;
				i++;
				break;
			case '-':
				array[p]--;
				i++;
				break;
			case '<':
				if (--p == -1) {
					std::cerr << std::endl << "Error: Out of bounds. (index -1)" << std::endl;
					return -1;
				}
				i++;
				break;
			case '>':
				if (++p == ARRAY_SIZE) {
					std::cerr << std::endl << "Error: Out of bounds. (index " << ARRAY_SIZE << ")" << std::endl;
					return -1;
				}
				i++;
				break;
			case '[':
				if (array[p] == 0) {
					i = loop[i];
				} else {
					i++;
				}
				break;
			case ']':
				if (array[p] != 0) {
					i = loop[i];
				} else {
					i++;
				}
				break;
			case '.':
				std::cout << array[p];
				i++;
				break;
			case ',':
				if (std::cin.get(c)) {
					array[p] = c;
				}
				i++;
				break;
		}
	}
	return 0;
}
