# March 2nd, 2022

file = open("input.txt", "r")

alphabet = "abcdefghijklmnopqrstuvwxyz"
registerA = {}
registerB = {}
for c in alphabet:
	registerA[c] = 0
	registerB[c] = 0
registerB["p"] = 1

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

def value(register, x):
	if isinstance(x, int):
		return x
	elif x not in alphabet:
		return int(x)
	return register[x]

ai = 0
bi = 0
a = []
b = []
count = 0
while True:
	# A
	if ai >= 0 and ai < len(instructions):
		instruction = instructions[ai][0]
		x = instructions[ai][1]
		y = instructions[ai][2]
		if instruction == "snd":
			b.append(value(registerA, x))
		elif instruction == "set":
			registerA[x] = value(registerA, y)
		elif instruction == "add":
			registerA[x] += value(registerA, y)
		elif instruction == "mul":
			registerA[x] *= value(registerA, y)
		elif instruction == "mod":
			registerA[x] %= value(registerA, y)
		elif instruction == "rcv":
			if len(a) == 0:
				ai -= 1
			else:
				registerA[x] = a[0]
				a = a[1:]
		elif instruction == "jgz":
			if value(registerA, x) > 0:
				ai += value(registerA, y) - 1
		ai += 1

	# B
	if bi >= 0 and bi < len(instructions):
		instruction = instructions[bi][0]
		x = instructions[bi][1]
		y = instructions[bi][2]
		if instruction == "snd":
			a.append(value(registerB, x))
			count += 1
		elif instruction == "set":
			registerB[x] = value(registerB, y)
		elif instruction == "add":
			registerB[x] += value(registerB, y)
		elif instruction == "mul":
			registerB[x] *= value(registerB, y)
		elif instruction == "mod":
			registerB[x] %= value(registerB, y)
		elif instruction == "rcv":
			if len(b) == 0:
				if instructions[ai][0] == "rcv" and len(a) == 0:
					break # Deadlock
				bi -= 1
			else:
				registerB[x] = b[0]
				b = b[1:]
		elif instruction == "jgz":
			if value(registerB, x) > 0:
				bi += value(registerB, y) - 1
		bi += 1
print(count)
