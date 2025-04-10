#!/usr/bin/python3
totalCousins = 100
i = 0
count = 0

def isCousin(num):
    if num < 2:
        return False
    for i in range(2, num):
        if num % i == 0:
            return False
    return True


while count < totalCousins:
    if isCousin(i):
        print(count, i)
        count += 1
    i += 1
    