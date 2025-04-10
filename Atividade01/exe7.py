number = 0

while True:
    number = int(input("Digite um número ímpar: "))
    if number % 2 == 0:
        print("Digite um número ímpar!")
    else:
        break

result = (number-1)^2 - (number+2)^2
print(result)