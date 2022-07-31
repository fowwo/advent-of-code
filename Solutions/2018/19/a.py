# July 31st, 2022

file = open("input.txt", 'r')

instructions = []
ip = int(file.readline()[4])
for line in file:
	s = line.strip().split(' ')
	instructions.append(f"{s[0]}({s[1]},{s[2]},{s[3]})")

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

# Execute
register = [ 0, 0, 0, 0, 0, 0 ]
while register[ip] in range(len(instructions)):
	exec(instructions[register[ip]])
	register[ip] += 1
print(register[0])
