# August 8th, 2022

def evaluate(expression):
	i = expression.find('(')
	while i != -1:
		depth = 0
		j = i + 1
		while expression[j] != ')' or depth > 0:
			if expression[j] == '(': depth += 1
			elif expression[j] == ')': depth -= 1
			j += 1
		v = evaluate(expression[i + 1:j])
		expression = f"{expression[:i]}{str(v)}{expression[j + 1:]}"
		i = expression.find('(')
	
	expression = expression.split(' ')
	for i in range(len(expression)):
		if expression[i].isnumeric():
			expression[i] = int(expression[i])
	
	# Addition
	i = 1
	while i < len(expression):
		op = expression.pop(i)
		if op == '+':
			expression[i - 1] += expression[i]
			del expression[i]
		else:
			i += 1

	# Multiplication
	m = 1
	for x in expression: m *= x
	return m

file = open(f"{__file__}/../input.txt", "r")

s = 0
for line in file:
	s += evaluate(line.strip())
file.close()

print(s)
