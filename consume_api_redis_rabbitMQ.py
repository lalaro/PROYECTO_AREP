import requests
import concurrent.futures
import random
import time

URL = "http://localhost:8082/data"  # Cambia el puerto si tu servicio usa otro

def send_request(i):
    payload = {
        "paied": random.choice([True, False]),
        "data": f"mensaje {i}",
        "isPremium": random.choice([True, False])
    }
    try:
        response = requests.post(URL, json=payload)
        return f"Solicitud {i}: {response.status_code} - {response.text}"
    except Exception as e:
        return f"Solicitud {i}: ERROR - {e}"

if __name__ == "__main__":
    NUM_SOLICITUDES = 2000  # Número de solicitudes concurrentes
    print("Este es el número de solicitudes ... ", NUM_SOLICITUDES)

    with concurrent.futures.ThreadPoolExecutor(max_workers=10) as executor:
        futures = [executor.submit(send_request, i) for i in range(NUM_SOLICITUDES)]
        for future in concurrent.futures.as_completed(futures):
            print(future.result())