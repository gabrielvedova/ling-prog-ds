#!/usr/bin/python3

answer = input("Forneça uma expressão matemática simples, contendo um espaço entre os números e o operador(Ex: 2 + 2): ").split(" ")

def sum(n1, n2):
    print(float(n1) + float(n2))
def sub(n1, n2):
    print(float(n1) - float(n2))
def mult(n1, n2):
    print(float(n1) * float(n2))
def div(n1, n2):
    print(float(n1) / float(n2))

def check_operator(n1, op, n2):
    if op == "+":
        return sum(n1, n2)
    elif op == "-":
        return sub(n1, n2)
    elif op == "*":
        return mult(n1, n2)
    elif op == "/":
        return div(n1, n2)
    else:
        print("Operador inválido")

check_operator(*answer)
