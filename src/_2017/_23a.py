# March 3rd, 2022

file = open("input/2017/23.txt", "r")

register = {}
for c in "abcdefgh":
	register[c] = 0

instructions = []
for line in file:
	split = line.strip().split(" ")
	instruction = [ split[0] ]
	if split[1] in register:
		instruction.append(split[1])
	else:
		instruction.append(int(split[1]))
	if split[2] in register:
		instruction.append(split[2])
	else:
		instruction.append(int(split[2]))
	instructions.append(instruction)

def value(x):
	if isinstance(x, int):
		return x
	return register[x]

i = 0
count = 0
while i >= 0 and i < len(instructions):
	instruction = instructions[i][0]
	x = instructions[i][1]
	y = instructions[i][2]
	if instruction == "set":
		register[x] = value(y)
	elif instruction == "sub":
		register[x] -= value(y)
	elif instruction == "mul":
		register[x] *= value(y)
		count += 1
	elif instruction == "jnz":
		if value(x) != 0:
			i += value(y) - 1
	i += 1
print(count)
