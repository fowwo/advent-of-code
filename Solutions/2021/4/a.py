# March 10th, 2022

file = open(f"{__file__}/../input.txt", "r")

draw = [int(x) for x in file.readline().strip().split(',')]
file.readline() # Empty line after draw order
lines = [line.strip() for line in file]

boards = []
i = 0
while i < len(lines):
	board = []
	board.append([int(x) for x in lines[i].split()])
	board.append([int(x) for x in lines[i+1].split()])
	board.append([int(x) for x in lines[i+2].split()])
	board.append([int(x) for x in lines[i+3].split()])
	board.append([int(x) for x in lines[i+4].split()])
	boards.append(board)
	i += 6

def check(board):
	# Row 
	for row in board:
		win = True
		for cell in row:
			if cell != 'x':
				win = False
				break
		if win: return True
	
	# Column
	for i in range(0, 5):
		win = True
		for row in board:
			if row[i] != 'x':
				win = False
				break
		if win: return True
	return False

for number in draw:
	for board in boards:
		found = False
		for row in board:
			for i in range(0, 5):
				if row[i] == number:
					row[i] = "x"
					win = check(board)
					if win:
						score = 0
						for row in board:
							for cell in row:
								if cell != 'x':
									score += cell
						print(score * number)
						exit()
					found = True
					break
			if found: break
