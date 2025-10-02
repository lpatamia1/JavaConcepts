import requests
import pandas as pd

def fetch_air_quality(city="Chicago"):
    """
    Fetch real-time air quality data using OpenAQ API.
    """
    url = f"https://api.openaq.org/v2/latest?city={city}"
    response = requests.get(url)
    data = response.json()

    results = {}
    if "results" in data:
        for location in data["results"]:
            for measurement in location["measurements"]:
                parameter = measurement["parameter"]
                value = measurement["value"]
                results[parameter] = value
    return results

def fetch_water_usage(city="Chicago"):
    """
    Placeholder: Fetch water usage data from a city API or dataset.
    Currently returns mock data.
    """
    mock_data = {
        "daily_usage_gal": 120,
        "weekly_avg_gal": 850
    }
    return mock_data

def fetch_food_sustainability(city="Chicago"):
    """
    Placeholder: Use USDA or FAO datasets.
    Currently returns mock scores.
    """
    mock_data = {
        "sustainability_score": 70
    }
    return mock_data

def fetch_all_data(city="Chicago"):
    """
    Combine all environmental metrics.
    """
    data = {
        "city": city,
        "air_quality": fetch_air_quality(city),
        "water_usage": fetch_water_usage(city),
        "food_sustainability": fetch_food_sustainability(city)
    }
    return data
