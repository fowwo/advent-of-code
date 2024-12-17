# December 16th, 2024

import re

file = open(f"{__file__}/../input.txt", "r")
program = list(map(int, re.findall(r"\d+", file.read())))
a, b, c = program.pop(0), program.pop(0), program.pop(0)
file.close()

def combo(x):
	return x if x <= 3 else [ a, b, c ][x - 4]

i = 0
out = []
while i < len(program):
	match program[i]:
		case 0:
			a >>= combo(program[i + 1])
		case 1:
			b ^= program[i + 1]
		case 2:
			b = combo(program[i + 1]) % 8
		case 3:
			if a:
				i = program[i + 1] - 2
		case 4:
			b ^= c
		case 5:
			out.append(combo(program[i + 1]) % 8)
		case 6:
			b = a >> combo(program[i + 1])
		case 7:
			c = a >> combo(program[i + 1])
	i += 2
print(",".join(str(x) for x in out))
