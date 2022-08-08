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
	x = int(expression[0])
	i = 2
	while i <= len(expression):
		y = int(expression[i])
		if expression[i - 1] == '+': x += y
		else: x *= y
		i += 2
	return x

file = open(f"{__file__}/../input.txt", "r")

s = 0
for line in file:
	s += evaluate(line.strip())
file.close()

print(s)
