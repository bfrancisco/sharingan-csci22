# html to Java 2DPath
# Bryan Francisco

# worklow: export SVG from Illustrator -> import to Inkscape -> save as HTML -> copy-paste selected HTML code here as input -> copy-paste to Java
# made tomoe and sharp-ellipse shapes using this.

# very ugly code. i know.

a = []
s = input().strip()
path = "tomoe" # change this to the Path2D variable name
while (s != 'e'):
    s = s[4:]
    i = 0
    func = ""
    while (s[i] != '('):
        func += s[i]
        i += 1

    valstr = s[i+1: len(s)-2]
    vals = valstr.split(", ")
    print(vals)
    f = ""
    
    if func == "moveTo":
        f = "moveTo"
    elif func == "bezierCurveTo":
        f = "curveTo"
    elif func == "lineTo":
        f = "lineTo"
    elif func == "closePath":
        a.append(path + ".closePath();")
        continue
    xy = 0 # x = 0 , y = 1

    ret = ''
    ret += path + '.' + f + '('

    for v in vals[:-1]:
        if xy == 0:
            ret += "x+scale*(" + v + '), '
        else:
            ret += "y+scale*(" + v + '), '
        xy = 1 - xy

    if xy == 0:
        ret += "x+scale*(" + vals[-1] + ')'
    else:
        ret += "y+scale*(" + vals[-1] + ')'

    ret += ');'
    a.append(ret)
    s = input().strip()

print(*a, sep='\n')