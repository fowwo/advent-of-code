# March 1st, 2022

file = open("input/2017/18.txt", "r")

alphabet = "abcdefghijklmnopqrstuvwxyz"
register = {}
for c in alphabet:
	register[c] = 0

instructions = []
for line in file:
	split = line.strip().split(" ")
	instruction = [ split[0], split[1] ]
	if len(split) == 3:
		if split[2] not in alphabet:
			instruction.append(int(split[2]))
		else:
			instruction.append(split[2])
	else:
		instruction.append("")
	instructions.append(instruction)

def value(x):
	if isinstance(x, int):
		return x
	return register[x]

i = 0
sound = 0
while i >= 0 and i < len(instructions):
	instruction = instructions[i][0]
	x = instructions[i][1]
	y = instructions[i][2]
	if instruction == "snd":
		sound = value(x)
	elif instruction == "set":
		register[x] = value(y)
	elif instruction == "add":
		register[x] += value(y)
	elif instruction == "mul":
		register[x] *= value(y)
	elif instruction == "mod":
		register[x] %= value(y)
	elif instruction == "rcv":
		if register[x] != 0:
			print(sound)
			break
	elif instruction == "jgz":
		if register[x] > 0:
			i += value(y) - 1
	i += 1
