# July 29th, 2022

file = open("input.txt", 'r')

samples = []
line = file.readline().strip()
while line != "":
	before = [ int(x) for x in line[9:-1].split(", ") ]
	instructions = [ int(x) for x in file.readline().strip().split(" ") ]
	after = [ int(x) for x in file.readline().strip()[9:-1].split(", ") ]
	samples.append([ before, instructions, after ])
	file.readline()
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

operations = [ addr, addi, mulr, muli, banr, bani, borr, bori, setr, seti, gtir, gtri, gtrr, eqir, eqri, eqrr ]
total = 0
for sample in samples:
	count = 0
	for op in operations:
		register = [ x for x in sample[0] ]
		a = sample[1][1]
		b = sample[1][2]
		c = sample[1][3]
		op(a, b, c)
		if register == sample[2]:
			count += 1
			if count == 3:
				total += 1
				break
print(total)
