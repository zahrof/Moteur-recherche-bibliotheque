
import random


def alea_text(length):
    return "".join([chr(random.randint(ord('a'), ord('z')+1)) for i in range(length)])

def bias(length):
    return [sum(list(range(1,i+1))) for i in range(1,length+1)]

def biased_letter(order):
    frq = bias(26)
    r = random.randint(0,frq[-1]-1)
    for i in range(len(frq)):
        if r < frq[i]:
            return order[i]

def biased_word(length, order):
    return "".join([biased_letter(order) for i in range(length)])

def biased_line(length, wlength, order):
    return " ".join([biased_word(wlength, order) for i in range(length//wlength)])

def biased_text(length, llength, wlength):
    order = [chr(97+i) for i in range(26)]
    random.shuffle(order)
    return " \n".join([biased_line(llength, wlength, order) for i in range(length//llength)])

if __name__ == "__main__":
    for i in range(1667):
        with open(str(i) + ".txt", "w") as f:
            f.write(biased_text(10000, 80, 5))