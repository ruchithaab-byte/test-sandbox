"""
Example API client for testing agent capabilities.
"""

import requests
from typing import Dict, Any, Optional


class ExampleAPIClient:
    """Simple API client for demonstration."""
    
    def __init__(self, base_url: str = "https://api.example.com"):
        self.base_url = base_url
        self.session = requests.Session()
    
    def get(self, endpoint: str, params: Optional[Dict[str, Any]] = None) -> Dict[str, Any]:
        """Make a GET request."""
        url = f"{self.base_url}/{endpoint.lstrip('/')}"
        response = self.session.get(url, params=params)
        response.raise_for_status()
        return response.json()
    
    def post(self, endpoint: str, data: Optional[Dict[str, Any]] = None) -> Dict[str, Any]:
        """Make a POST request."""
        url = f"{self.base_url}/{endpoint.lstrip('/')}"
        response = self.session.post(url, json=data)
        response.raise_for_status()
        return response.json()
    
    def health_check(self) -> bool:
        """Check if API is healthy."""
        try:
            result = self.get("/health")
            return result.get("status") == "healthy"
        except Exception:
            return False


if __name__ == "__main__":
    client = ExampleAPIClient()
    print(f"Health check: {client.health_check()}")
