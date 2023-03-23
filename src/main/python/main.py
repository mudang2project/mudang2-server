import sys
import pandas as pd
import numpy as np
import joblib


def distance(x1, y1, x2, y2, a, b):
    x1 = x1 * 100000
    y1 = y1 * 100000
    x2 = x2 * 100000
    y2 = y2 * 100000
    a = a * 100000
    b = b * 100000

    area = abs((x1 - a) * (y2 - b) - (y1 - b) * (x2 - a))
    AB = (((x1 - x2) ** 2 + (y1 - y2) ** 2) ** 0.5)
    AP = (((x1 - a) ** 2 + (y1 - b) ** 2) ** 0.5)
    BP = (((x2 - a) ** 2 + (y2 - b) ** 2) ** 0.5)

    length = [AB, AP, BP]
    length.sort()

    if length[2] ** 2 > (length[0] ** 2 + length[1] ** 2) and length[2] != AB:  # 둔각삼각형이면
        return min([AP, BP]) * 1
    else:
        return area / AB * 1


def min_distance(a):
    return min(a)


def call_model():
    return joblib.load('./svm_model.pkl')


def run_model(model, lat, lon):
    return model.predict([lat, lon])


def min_correction(lat, lon):
    road = [[37.4519, 127.1312], [37.4526, 127.1307], [37.4526, 127.1305], [37.4525, 127.1300], [37.4521, 127.1295],
            [37.4517, 127.1275], [37.4506, 127.1275], [37.450453, 127.127903], [37.449727, 127.127760],
            [37.449642, 127.128015], [37.450453, 127.127903], [37.450058, 127.129375], [37.450934, 127.129774],
            [37.450058, 127.129375], [37.4499, 127.1299], [37.4509, 127.1303], [37.4512, 127.1308], [37.4522, 127.1314],
            [37.4524, 127.1319], [37.4531, 127.1336], [37.4535, 127.1342], [37.4536, 127.1345], [37.4537, 127.1347],
            [37.4541, 127.1348], [37.4555, 127.1347], [37.455883, 127.134630], [37.455713, 127.134075],
            [37.455227, 127.133994], [37.455169, 127.134695], [37.455446, 127.134335]]

    a = lat
    b = lon
    distance_list = []

    for i in range(0, len(road) - 1):
        distance_list.append(distance(road[i][0], road[i][1], road[i + 1][0], road[i + 1][1], a, b))

    j = distance_list.index(min_distance(distance_list))
    x, y = correction(road[j][0], road[j][1], road[j + 1][0], road[j + 1][1], a, b)

    return str(x + ' ' + y)


def correction(x1, y1, x2, y2, a, b):
    #     area = abs((x1-a) * (y2-b) - (y1-b) * (x2 - a))
    AB = (((x1 - x2) ** 2 + (y1 - y2) ** 2) ** 0.5)
    AP = (((x1 - a) ** 2 + (y1 - b) ** 2) ** 0.5)
    BP = (((x2 - a) ** 2 + (y2 - b) ** 2) ** 0.5)

    length = [AB, AP, BP]
    length.sort()

    if length[2] ** 2 > (length[0] ** 2 + length[1] ** 2) and length[2] != AB:  # 둔각삼각형이면
        if AP >= BP:
            return x1, y1
        else:
            return x2, y2
    else:
        x = (a * (x2 - x1) ** 2 + x1 * (y2 - y1) ** 2 + (b - y1) * (x2 - x1) * (y2 - y1)) / (
                (y2 - y1) ** 2 + (x2 - x1) ** 2)
        y = (y2 - y1) / (x2 - x1) * (x - x1) + y1
        return x, y


def main(lat, lon):
    model = call_model()
    boolean = run_model(model, float(lat), float(lon))
    if boolean == 'T':
        return [lat,lon]
    elif boolean == 'F':
        return [min_correction(boolean, lat, lon)]
