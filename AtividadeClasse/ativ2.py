#!/usr/bin/python3

distance = input("Forneça uma distância em km, m ou litros(Ex: 31 km ou 100 l): ").split(" ")

def km_to_mi(d):
    print(f"{float(d) * 0.621371} mi")

def m_to_cm(d):
    print(f"{float(d) * 100} cm")

def l_to_ml(d):
    print(f"{float(d) * 1000} ml")

def check_unit(d, unit):
    if unit == "km":
        return km_to_mi(d)
    elif unit == "m":
        return m_to_cm(d)
    elif unit == "l":
        return l_to_ml(d)
    else:
        print("Unidade inválida")

check_unit(*distance)