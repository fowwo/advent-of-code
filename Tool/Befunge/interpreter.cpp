// December 16th, 2024

#include <cstring>
#include <fstream>
#include <iostream>
#include <random>
#include <stack>
#include <string>
#include <vector>

int main(int argc, char *argv[]) {
	if (argc == 1) {
		std::cerr << std::endl << "Missing file path." << std::endl;
		return 1;
	}

	// Open the Befunge file.
	std::ifstream file(argv[1]);
	if (!file.is_open()) {
		std::cerr << std::endl << "Unable to open file." << std::endl;
		return 1;
	}

	// Initialize the playfield.
	const int MAX_WIDTH = 80, MAX_HEIGHT = 25;
	char program[MAX_HEIGHT][MAX_WIDTH];
	std::memset(program, ' ', sizeof(program));
	std::string line;
	for (int i = 0; std::getline(file, line); i++) {
		if (line.size() == 0) continue;
		if (line.size() > MAX_WIDTH) {
			std::cerr << std::endl << "Maximum program width exceeded. (" << line.size() << " of " << MAX_WIDTH << " characters)" << std::endl;
			return 1;
		}
		if (i > MAX_HEIGHT) {
			std::cerr << std::endl << "Maximum program height exceeded. (>" << MAX_HEIGHT << " rows)" << std::endl;
			return 1;
		}
		std::copy(line.begin(), line.end(), program[i]);
	}
	file.close();

	// Run the code.
	std::stack<long> s;
	unsigned char x = 0, y = 0, d = 0;
	bool string_mode = false;
	std::random_device dev;
	std::mt19937 rng(dev());
	std::uniform_int_distribution<std::mt19937::result_type> random(0, 3);
	while (true) {
		if (string_mode && program[y][x] != '"') {
			s.push(static_cast<char>(program[y][x]));
		} else {
			long a = 0, b = 0, z = 0;
			std::string input;
			char c;
			switch (program[y][x]) {
				case '+':
					if (!s.empty()) { b = s.top(); s.pop(); }
					if (!s.empty()) { a = s.top(); s.pop(); }
					s.push(a + b);
					break;
				case '-':
					if (!s.empty()) { b = s.top(); s.pop(); }
					if (!s.empty()) { a = s.top(); s.pop(); }
					s.push(a - b);
					break;
				case '*':
					if (!s.empty()) { b = s.top(); s.pop(); }
					if (!s.empty()) { a = s.top(); s.pop(); }
					s.push(a * b);
					break;
				case '/':
					if (!s.empty()) { b = s.top(); s.pop(); }
					if (!s.empty()) { a = s.top(); s.pop(); }
					s.push(a / b);
					break;
				case '%':
					if (!s.empty()) { b = s.top(); s.pop(); }
					if (!s.empty()) { a = s.top(); s.pop(); }
					s.push(a % b);
					break;
				case '!':
					if (!s.empty()) { a = s.top(); s.pop(); }
					s.push(a ? 0 : 1);
					break;
				case '`':
					if (!s.empty()) { b = s.top(); s.pop(); }
					if (!s.empty()) { a = s.top(); s.pop(); }
					s.push(a > b ? 1 : 0);
					break;
				case '>':
					d = 0;
					break;
				case 'v':
					d = 1;
					break;
				case '<':
					d = 2;
					break;
				case '^':
					d = 3;
					break;
				case '?':
					d = random(rng);
					break;
				case '_':
					if (!s.empty()) { a = s.top(); s.pop(); }
					d = a ? 2 : 0;
					break;
				case '|':
					if (!s.empty()) { a = s.top(); s.pop(); }
					d = a ? 3 : 1;
					break;
				case '"':
					string_mode = !string_mode;
					break;
				case ':':
					if (!s.empty()) { a = s.top(); s.pop(); }
					s.push(a);
					s.push(a);
					break;
				case '\\':
					if (!s.empty()) { b = s.top(); s.pop(); }
					if (!s.empty()) { a = s.top(); s.pop(); }
					s.push(b);
					s.push(a);
					break;
				case '$':
					if (!s.empty()) s.pop();
					break;
				case '.':
					if (!s.empty()) { a = s.top(); s.pop(); }
					std::cout << a << ' ';
					break;
				case ',':
					if (!s.empty()) { a = s.top(); s.pop(); }
					std::cout << static_cast<char>(a);
					break;
				case '#':
					switch (d) {
						case 0: x++; if (x == MAX_WIDTH) x = 0; break;
						case 1: y++; if (y == MAX_HEIGHT) y = 0; break;
						case 2: if (x == 0) x = MAX_WIDTH; x--; break;
						case 3: if (y == 0) y = MAX_HEIGHT; y--; break;
					}
					break;
				case 'g':
					if (!s.empty()) { b = s.top(); s.pop(); }
					if (!s.empty()) { a = s.top(); s.pop(); }
					s.push(0 <= a && a < MAX_WIDTH && 0 <= b && b < MAX_HEIGHT ? program[b][a] : 0);
					break;
				case 'p':
					if (!s.empty()) { b = s.top(); s.pop(); }
					if (!s.empty()) { a = s.top(); s.pop(); }
					if (!s.empty()) { z = s.top(); s.pop(); }
					if (0 <= a && a < MAX_WIDTH && 0 <= b && b < MAX_HEIGHT) {
						program[b][a] = z;
						break;
					}
					std::cerr << std::endl << "Error: Put out of bounds. (" << a << ", " << b << ")" << std::endl;
					return 1;
				case '&':
					std::cin >> input;
					s.push(std::stol(input));
					break;
				case '~':
					s.push(std::cin.get(c) ? c : -1);
					break;
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
					s.push(program[y][x] - '0');
					break;
				case ' ':
					break;
				case '@':
					return 0;
				default:
					std::cerr << std::endl << "Error: Unexpected command: " << program[y][x] << " (" << static_cast<int>(x) << ", " << static_cast<int>(y) << ")" << std::endl;
					return -1;
			}
		}
		switch (d) {
			case 0: x++; if (x == MAX_WIDTH) x = 0; break;
			case 1: y++; if (y == MAX_HEIGHT) y = 0; break;
			case 2: if (x == 0) x = MAX_WIDTH; x--; break;
			case 3: if (y == 0) y = MAX_HEIGHT; y--; break;
		}
	}
	return 0;
}
