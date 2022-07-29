# July 29th, 2022

file = open("input.txt", 'r')

samples = []
instructions = []
line = file.readline().strip()
while line != "":
	before = [ int(x) for x in line[9:-1].split(", ") ]
	instruction = [ int(x) for x in file.readline().strip().split(" ") ]
	after = [ int(x) for x in file.readline().strip()[9:-1].split(", ") ]
	samples.append([ before, instruction, after ])
	file.readline()
	line = file.readline().strip()
while line == "": line = file.readline().strip()
while line != "":
	instructions.append([ int(x) for x in line.split(' ') ])
	line = file.readline().strip()
file.close()

def addr(a, b, c): register[c] = register[a] + register[b]
def addi(a, b, c): register[c] = register[a] + b
def mulr(a, b, c): register[c] = register[a] * register[b]
def muli(a, b, c): register[c] = register[a] * b
def banr(a, b, c): register[c] = register[a] & register[b]
def bani(a, b, c): register[c] = register[a] & b
def borr(a, b, c): register[c] = register[a] | register[b]
def bori(a, b, c): register[c] = register[a] | b
def setr(a, b, c): register[c] = register[a]
def seti(a, b, c): register[c] = a
def gtir(a, b, c): register[c] = 1 if a > register[b] else 0
def gtri(a, b, c): register[c] = 1 if register[a] > b else 0
def gtrr(a, b, c): register[c] = 1 if register[a] > register[b] else 0
def eqir(a, b, c): register[c] = 1 if a == register[b] else 0
def eqri(a, b, c): register[c] = 1 if register[a] == b else 0
def eqrr(a, b, c): register[c] = 1 if register[a] == register[b] else 0

# Determine opcode operation
operations = [ addr, addi, mulr, muli, banr, bani, borr, bori, setr, seti, gtir, gtri, gtrr, eqir, eqri, eqrr ]
possible = [ [ x for x in operations] for _ in operations ]
for sample in samples:
	opcode = sample[1][0]
	a = sample[1][1]
	b = sample[1][2]
	c = sample[1][3]
	for operation in possible[opcode]:
		register = [ x for x in sample[0] ]
		operation(a, b, c)
		if register != sample[2]:
			possible[opcode].remove(operation)
done = False
while not done:
	done = True
	for x in possible:
		if len(x) == 1:
			for y in possible:
				if x != y and x[0] in y:
					y.remove(x[0])
		else: done = False

# Execute
for i in range(len(possible)): possible[i] = possible[i][0]
register = [ 0, 0, 0, 0 ]
for instruction in instructions:
	opcode = instruction[0]
	a = instruction[1]
	b = instruction[2]
	c = instruction[3]
	possible[opcode](a, b, c)
print(register[0])
